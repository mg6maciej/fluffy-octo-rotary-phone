#!/bin/bash

if [ $# -eq 0 ]; then
    echo "USAGE: ./release.sh 6.6.6"
    exit
fi

DIR=$(dirname $0)
VERSION_NAME=$1
ARR=(${VERSION_NAME//./ })
VERSION_CODE=$((  1000000*${ARR[0]} + 1000*${ARR[1]} + 2*${ARR[2]}  ))
NEXT_VERSION_CODE=$((  $VERSION_CODE + 1  ))
NEXT_BUGFIX=$((  ${ARR[2]} + 1  ))
NEXT_VERSION_NAME="${ARR[0]}.${ARR[1]}.${NEXT_BUGFIX}-SNAPSHOT"

git stash
git checkout develop
git pull
git checkout -b release/$VERSION_NAME

sed -iE \
	-e "s/versionName \".*\"/versionName \"$VERSION_NAME\"/" \
	-e "s/versionCode .*/versionCode $VERSION_CODE/" \
	$DIR/build.gradle

git commit -a -m "Update version in build.gradle to $VERSION_NAME."
git checkout master
git pull
git merge --no-ff --no-edit release/$VERSION_NAME
git push
git tag $VERSION_NAME
git push --tags
git checkout develop
git merge --no-ff --no-edit release/$VERSION_NAME

sed -iE \
	-e "s/versionName \".*\"/versionName \"$NEXT_VERSION_NAME\"/" \
	-e "s/versionCode .*/versionCode $NEXT_VERSION_CODE/" \
	$DIR/build.gradle

git commit -a -m "Update version in build.gradle to $NEXT_VERSION_NAME."
git push
git branch -d release/$VERSION_NAME
