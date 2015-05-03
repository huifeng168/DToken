package com.connsec.db.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.connsec.db.persistence.IBaseMapper;
import com.connsec.domain.BaseDomain;
import com.connsec.util.StringUtils;
import com.connsec.web.WebContext;
import com.connsec.web.component.Grid;


/**
 * @author Crystal.Sea
 *
 * @param <T>
 */
public  class  BaseService <T extends BaseDomain> {
	
	final static Logger log = Logger.getLogger(BaseService.class);
	
	/**
	 * mapper class
	 */
	private String mapperClass = "";
	
	/**
	 * entity Class
	 */
	@SuppressWarnings("rawtypes")
	private Class entityClass;
	
	/**
	 * mapper 
	 */
	private IBaseMapper<T> mapper = null;
	
	//TODO 
	public BaseService() {}
	
	/**
	 * Load mapperClass by class type
	 * @param cls
	 */
	@SuppressWarnings("unchecked")
	public BaseService(@SuppressWarnings("rawtypes") Class cls) {
		log.trace("class : " + cls.getSimpleName());
		mapperClass = cls.getSimpleName();
		Type[] pType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
		if (pType != null && pType.length >= 1) {
			this.entityClass = (Class<T>) pType[0];
		} else {
			throw new RuntimeException("invalide initail, need generic type parameter!");
		}
		log.trace("class : " + entityClass.getSimpleName());
	}

	/**
	 *  Load mapperClass by class name
	 * @param mapperClass
	 */
	public BaseService(String mapperClass) {
		log.trace("class : " + mapperClass);
		this.mapperClass = mapperClass;
	}

	//TODO get or set mapper
	/**
	 * Load Mapper from spring container by mapperClass as bean id
	 * @return IBaseMapper
	 */
	@SuppressWarnings( { "finally", "unchecked" })
	public IBaseMapper<T> getMapper() {
		try {
			if(mapper == null) {
				String mapperClassBean=mapperClass.toLowerCase().charAt(0)+mapperClass.substring(1);
				log.info("mapperClass Bean is " +mapperClassBean);
				mapper = (IBaseMapper<T>) WebContext.getBean(mapperClassBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return mapper;
		}
	}

	public void setMapper(IBaseMapper<T> mapper) {
		this.mapper = mapper;
	}

	//TODO follow function for Query
	
	/**
	 * query page list entity by entity 
	 * @param entity
	 * @return
	 */
	public Grid<T> grid(T entity) {
		Integer totalCount = parseCount(getMapper().count(entity));
		if(totalCount == 0) {
			return new Grid<T>();
		}
		
		int totalPage = calculateTotalPage(entity,totalCount);
		
		if(totalPage == 0) {
			return new Grid<T>();
		}
		
		if(totalPage < entity.getPage()) {
			entity.setPage(totalPage);
			entity.setStartRow(calculateStartRow(totalPage ,entity.getPageResults()));
		}
		entity.setPageable(true);
		return new Grid<T>(entity.getPage(),entity.getPageResults(),totalPage,totalCount,getMapper().grid(entity));
	}
	
	/**
	 * query Count by entity 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer count(T entity) {
		try {
			if(entity == null) {
				entity = (T) entityClass.newInstance();
			}
			Integer count=getMapper().count(entity);
			log.debug("queryCount count : "+count);
			return count;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<T> select(T entity) {
		try {
			return getMapper().select(entity);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  query list entity by entity 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(T entity) {
		try {
			if(entity == null) {
				entity = (T) entityClass.newInstance();
				return getMapper().query(entity);
			}
			return getMapper().query(entity);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<T> findAll() {
		try {

			return getMapper().findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  query one entity by entity
	 * @param entity
	 * @return
	 */
	public T load(T entity) {
		try {
			return getMapper().load(entity);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * query one entity by entity id
	 * @param id
	 * @return
	 */
	public T get(String id) {
		try {
			return  getMapper().get(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO follow function for insert update and delete
	/**
	 * insert new entity
	 * @param entity
	 * @return
	 */
	public boolean insert(T entity) {
		try {
			
			if(StringUtils.isNullOrBlank(entity.getId())) {
				entity.genId();
			}
			if(StringUtils.isNullOrBlank(entity.getCreatedBy()) && WebContext.getUserInfo()!=null) {
				entity.setCreatedBy(WebContext.getUserInfo().getUsername());
			}
			entity.setCreatedDate(new Date());
			
			Integer count=getMapper().insert(entity);
			log.debug("insert count : "+count);
			
			return  count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * update entity
	 * @param entity
	 * @return
	 */
	public boolean update(T entity) {
		try {
			if(WebContext.getUserInfo() != null) {
				entity.setModifiedBy(WebContext.getUserInfo().getUsername());
			}
			entity.setModifiedDate(new Date());
			
			Integer count=getMapper().update(entity);
			log.debug("update count : "+count);
			
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * delete entity by entity
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity) {
		try {
			Integer count=getMapper().delete(entity);
			log.debug("delete count : "+count);
			
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean remove(String id){
		try {
			
			Integer count=getMapper().remove(id);
			log.debug("remove count : "+count);
			
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//TODO follow function for complex insert and delete
	/**
	 * batch insert entity
	 * @param listEntity
	 * @return
	 */
	public boolean batchInsert(List<T> listEntity){
		try {
			Integer count=getMapper().batchInsert(listEntity);
			log.debug("batchInsert count : "+count);
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * batch delete entity by ids
	 * @param ids
	 * @return
	 */
	public boolean batchDelete(List<String> ids) {
		try {
			Integer count=getMapper().batchDelete(ids);
			log.debug("batchDelete count : "+count);
			
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * logistic Delete
	 * @param entity
	 * @return
	 */
	public boolean logisticDelete(T entity) {
		try {
			Integer count=getMapper().logisticDelete(entity);
			log.debug("logisticDelete count : "+count);
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * logistic Batch Delete by ids
	 * @param ids
	 * @return
	 */
	public boolean logisticBatchDelete(List<String> ids) {
		try {
			Integer count=getMapper().logisticBatchDelete(ids);
			log.debug("logisticBatchDelete count : "+count);
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
	//TODO follow is  for query grid paging
	/**
	 * parse Object Count to Integer
	 * @param totalCount
	 * @return
	 */
	public Integer parseCount(Object totalCount){
		Integer retTotalCount=0;
		if(totalCount == null) {
			return retTotalCount;
		}else{
			retTotalCount=Integer.parseInt(totalCount.toString());
		}
		return retTotalCount;
	}
	
	/**
	 * calculate total Count
	 * @param entity
	 * @param totalCount
	 * @return
	 */
	public Integer calculateTotalPage(BaseDomain entity,Integer totalCount){
		return (totalCount + entity.getPageResults() - 1) / entity.getPageResults();
	}
	
	/**
	 * calculate StartRow
	 * @param page
	 * @param pageResults
	 * @return
	 */
	public Integer calculateStartRow(Integer page,Integer pageResults){
		return (page - 1) * pageResults;
	}
}
