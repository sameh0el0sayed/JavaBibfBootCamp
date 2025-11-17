# Get date and week number
$today = (Get-Date -Format "dd-MMM-yyyy")
$week  = (Get-Date -Format "ww")

# Commit message
$msg = "HW (Week $week) ($today)"

git add .
git commit -m "$msg"
git push
