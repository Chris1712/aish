# aish

A little binary that'll give your shell context to chatgpt, along with a question.
It'll try to return a command that you can inspect and then run. 

- Usage:
  - `c help me get the sound from this video as an mp3`

## Building
Includes the normal gradle wrapper, so `./gradlew build` should work.
To build a native binary, run the task `nativeBuild`, some notes:
- This requires JAVA_HOME to be set to a [graalvm installation](https://www.graalvm.org/latest/docs/getting-started/)
  - So EG `JAVA_HOME=/opt/graalvm-jdk-21.0.3+7.1 ./gradlew nativeCompile`
- In ubuntu, you may find you need some additional files:
  - `It appears as though libz:.a is missing` -> `sudo apt install zlib1g-dev`
- The binary will be output to `app/build/native/nativeCompile/aish`

## Installing
`cp app/build/native/nativeCompile/aish /usr/local/bin/`

## Costs
gpt-4o cost $5 for 1M input tokens, $15 for 1M output tokens.
So 1000 input tokens is 1/2 cent.
If we use 100 calls per day at 1000 tokens each that's 50 cents. Seems worthwhile!


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
- add a module system for prompt building
- support some kind of first keyword like 'nocall' to cheaply test prompt generation
