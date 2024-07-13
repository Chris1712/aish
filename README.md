# aish

A little binary that'll give your shell context to chatgpt, along with a question.
It'll try to return a command that you can inspect and then run. 

- Usage:
  - `c help me get the sound from this video as an mp3`

## Building
Includes the normal gradle wrapper, so `./gradlew build` should work.
To build a native binary, run the task `nativeBuild`, some notes:
- This requires JAVA_HOME to be set to a [graalvm installation](https://www.graalvm.org/latest/docs/getting-started/)
  - So EG `JAVA_HOME=/opt/graalvm-jdk-21.0.3+7.1 ./gradlew nativeBuild`
- In ubuntu, you may find you need some additional files:
  - `It appears as though libz:.a is missing` -> `sudo apt install zlib1g-dev`
- The binary will be output to `app/build/native/nativeCompile/aish`

## Installing
`cp app/build/native/nativeCompile/aish /usr/local/bin/`

# Goals

- I don't want a repl, I want something I can invoke
- Ideally it gets context when I do
  - pwd
  - ls
  - git status
  - history
  - shell output?
  - extendable here
  - clipboard?


## TODO
- pick an http client etc and write the api client
- come up with a way to build this into a nice binary
