# Get today's date
$today = Get-Date

# Calculate week number using ISO standard
$calendar = [System.Globalization.DateTimeFormatInfo]::CurrentInfo.Calendar
$week = $calendar.GetWeekOfYear($today,
    [System.Globalization.CalendarWeekRule]::FirstFourDayWeek,
    [DayOfWeek]::Monday)

# Format date
$todayFormatted = $today.ToString("dd-MMM-yyyy")

# Commit message
$msg = "HW (Week $week) ($todayFormatted)"



git add .
git commit -m "$msg"
git push
