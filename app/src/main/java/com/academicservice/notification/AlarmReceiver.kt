package com.academicservice.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val title = intent?.getStringExtra("title") ?: "تنبيه"
            val message = intent?.getStringExtra("message") ?: ""
            val assignmentId = intent?.getIntExtra("assignmentId", 0) ?: 0
            
            showNotification(it, title, message, assignmentId)
        }
    }
    
    private fun showNotification(context: Context, title: String, message: String, assignmentId: Int) {
        val notification = NotificationCompat.Builder(context, "assignment_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        
        NotificationManagerCompat.from(context).notify(assignmentId, notification)
    }
}
