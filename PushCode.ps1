# Get current date in format DD-MMM-YYYY
$today = (Get-Date).ToString("dd-MMM-yyyy")

# Commit message
$msg = "HW -> $today"

# Git add, commit, and push
git add .
git commit -m "$msg"
git push
