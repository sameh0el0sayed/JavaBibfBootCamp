# Get today's date
$today = Get-Date

# Week of month = ((day-1) / 7) + 1
$weekOfMonth = [math]::Ceiling($today.Day / 7)

# Format date
$todayFormatted = $today.ToString("dd-MMM-yyyy")

# Commit message
$msg = "HW (Week $weekOfMonth) ($todayFormatted)"

git add .
git commit -m "$msg"
git push
