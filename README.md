# AarTest
AarTest

./gradlew :app:assembleDebug -P autoIncrement=true
./gradlew :mylibrary:assembleDebug -P autoIncrement=true

In Gradle to read parameter
project.property("autoIncrement") - Value
project.hasProperty("autoIncrement") - To check

All Gradle Tasks
val runTasks = gradle.startParameter.taskNames