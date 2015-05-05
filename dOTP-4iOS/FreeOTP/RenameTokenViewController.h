//
// FreeOTP
//
// Authors: Nathaniel McCallum <npmccallum@redhat.com>
//
// Copyright (C) 2014  Nathaniel McCallum, Red Hat
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

@interface RenameTokenViewController : UITableViewController
@property (weak, nonatomic) IBOutlet UITextField *issuer;
@property (weak, nonatomic) IBOutlet UITextField *label;
@property (weak, nonatomic) IBOutlet UILabel *issuerDefault;
@property (weak, nonatomic) IBOutlet UILabel *labelDefault;
@property (weak, nonatomic) IBOutlet UIButton *button;
@property (weak, nonatomic) UIPopoverController *popover;
@property (nonatomic) NSUInteger token;
- (IBAction)cancelClicked:(id)sender;
- (IBAction)resetClicked:(id)sender;
- (IBAction)doneClicked:(id)sender;
@end
