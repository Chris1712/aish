# aish

A little binary that'll give your shell context to chatgpt, along with a question.
It'll try to return a command that you can inspect and then run. 

- Usage:
  - Basic example: `aish help me get the sound from this video as an mp3`
  - We can prepend `nocall` to show us the prompt without calling out API

## Building
Run `./gradlew build` or `./gradlew nativebuild` (automatically downloads GraalVM 21).
Native builds need a C compiler and zlib headers: `sudo apt install build-essential zlib1g-dev` on Ubuntu.
The binary is `app/build/native/nativeCompile/aish`.

## Installing
`cp app/build/native/nativeCompile/aish /usr/local/bin/`
`echo your-openai-api-key > ~/.aish/apikey`

## Costs
Uses `gpt-6-luna` with reasoning disabled for low latency: [$0.10 input / $0.50 output per million tokens](https://developers.openai.com/api/docs/models/gpt-6-luna).


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
