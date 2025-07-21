# aish

A little binary that'll give your shell context to chatgpt, along with a question.
It'll try to return a command that you can inspect and then run. 

- Usage:
  - Basic example: `aish help me get the sound from this video as an mp3`
  - We can prepend `nocall` to show us the prompt without calling out API

## Building
Includes the normal gradle wrapper, so `./gradlew build` should work.
To build a native binary, run the task `nativeBuild`, some notes:
- This requires JAVA_HOME to be set to a [graalvm installation](https://www.graalvm.org/latest/docs/getting-started/)
  - So EG `JAVA_HOME=/opt/graalvm-jdk-21.0.3+7.1 ./gradlew nativeCompile`
- In ubuntu, you may find you need some additional files:
  - `It appears as though libz:.a is missing` -> `sudo apt install zlib1g-dev`
- The binary will be output to `app/build/native/nativeCompile/aish`
- Note that getting graal to work with ktor etc. took a little fiddling; see reflect-config.json 
for some additional config that was necessary to fix runtime errors with the binary

## Installing
`cp app/build/native/nativeCompile/aish /usr/local/bin/`
`echo your-openai-api-key > ~/.aish/apikey`

## Costs
gpt-4o costs $2.50 for 1M input tokens, $10 for 1M output tokens.
So 1000 input tokens is 1/4 cent.
If we use 100 calls per day at 1000 tokens each that's 25 cents. Seems worthwhile!


# Goals

- I don't want a repl, I want something I can invoke
- Ideally it gets context when I do
  - git status
  - history
  - shell output?
  - extendable here
  - clipboard?


## TODO
- We should loop if there is info the model needs? It could ask us to give it the output of git status or something
- add a module system for prompt building
