#import "ZaptLocationPlugin.h"
#if __has_include(<zapt_location/zapt_location-Swift.h>)
#import <zapt_location/zapt_location-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "zapt_location-Swift.h"
#endif

@implementation ZaptLocationPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftZaptLocationPlugin registerWithRegistrar:registrar];
}
@end
