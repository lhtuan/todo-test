package com.test.todo.presentation.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Check if permissions are granted or not
 * @return list of un-granted permissions
 */
fun Context.checkPermissions(permissions: Array<String>): Array<String> {
    var unGrantedPermissions = arrayOf<String>()
    permissions.forEach { permission ->
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            unGrantedPermissions = unGrantedPermissions.plus(permission)
        }
    }
    return unGrantedPermissions
}
