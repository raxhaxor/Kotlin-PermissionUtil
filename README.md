# Kotlin-PermissionUtil

### USAGE ###
```
arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
                .withPermissions(this, 15000,
                    allowBody = {
                            toast("Permission Granted..")
                    },
                    discardBody = {
                            toast("Please Accept All Permissions.")
                        finish()

                    })

```
