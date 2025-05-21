package com.example.mandirinews.utils

import android.os.Build
import android.util.Log
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class HelperTime {
    companion object {
        private const val TAG = "HelperTime"

        /**
         * Formats an ISO 8601 date-time string (e.g., "2025-05-04T20:14:38Z")
         * into "dd MMMM, yyyy" format in Indonesian locale (e.g., "04 Mei, 2025").
         */
        fun formatDateOnly(isoTime: String?): String {
            if (isoTime.isNullOrEmpty()) {
                Log.w(TAG, "Input ISO time is null or empty")
                return ""
            }

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    val offsetDateTime = OffsetDateTime.parse(isoTime)
                    val zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.systemDefault())


                    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale("id", "ID"))
                    zonedDateTime.format(outputFormatter)
                } catch (e: DateTimeParseException) {
                    Log.e(TAG, "DateTimeParseException saat memformat waktu (API >= 26): ${e.message}", e)
                    isoTime
                } catch (e: Exception) {
                    Log.e(TAG, "Terjadi kesalahan saat memformat waktu: ${e.message}", e)
                    isoTime
                }
            } else {
                Log.e(TAG, "Gagal mem-parsing waktu: $isoTime dengan SimpleDateFormat")
                isoTime
            }
        }
    }
}