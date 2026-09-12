package com.academicservice.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.academicservice.data.models.Assignment
import java.util.*

class NotificationManager(private val context: Context) {
    
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    
    fun scheduleAssignmentReminder(assignment: Assignment) {
        val dueDate = Calendar.getInstance().apply {
            timeInMillis = assignment.dueDate
            add(Calendar.HOUR_OF_DAY, -24) // Remind 24 hours before
        }
        
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("title", "تذكير: واجب عاجل")
            putExtra("message", assignment.assignmentName)
            putExtra("assignmentId", assignment.id)
            action = "com.academicservice.ASSIGNMENT_REMINDER"
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            assignment.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            dueDate.timeInMillis,
            pendingIntent
        )
    }
    
    fun cancelAssignmentReminder(assignmentId: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            assignmentId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.cancel(pendingIntent)
    }
    
    fun scheduleLectureReminder(lectureDate: Long, topic: String, lectureId: Int) {
        val reminderDate = Calendar.getInstance().apply {
            timeInMillis = lectureDate
            add(Calendar.HOUR_OF_DAY, -1) // Remind 1 hour before
        }
        
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("title", "تذكير: محاضرة")
            putExtra("message", topic)
            putExtra("assignmentId", lectureId)
            action = "com.academicservice.LECTURE_REMINDER"
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            lectureId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            reminderDate.timeInMillis,
            pendingIntent
        )
    }
}
