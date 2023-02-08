latestTag=$(git describe --abbrev=0 --tags `git rev-list --tags --skip=1  --max-count=1` --match "dev-*")
echo "$latestTag"

releaseNotes=$(git log $latestTag...  --pretty='format:%Creset%s' --no-merges --grep='- ')
echo "$releaseNotes"