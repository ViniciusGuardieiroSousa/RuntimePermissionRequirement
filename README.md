# RuntimePermissionRequirement

This is a simple example of runtime permission requirement with koin and viewModel. When user aceppt the permission, the function permissionPermitted is called. Whe user deny once, the function permissionDenied is called, but when this permission is required again, is important tell why this permision is used and call the function requiredPermission again. The don't ask again box is controlled by using sharedPreferences.
