# Introduction

This is a tool to fetch property information from trademe insight. Although you can search via web ui at the moment, but geeks wants to do it automatically.

# Requirement

JRE installed and java in your path.
You have to supply your own google map api key.

# Build prerequisites

Assuming you are familar with clojure toolchain and you have lein.

# Build
```bash
lein uberjar
```

# Usage

Copy the compiled standalone jar in your target folder to the folder where you stored downloaded barfoot auction result files, then run

```bash
java -jar YOUR_BUILD_JAR_NAME -k YOUR_API_KEY_HERE -a "PROPERTY ADDRESS YOU WANT TO RESEARCH"
```

It will return json on the console.

If you want to search a list of addresses, you might want to exploit xargs in *nix.
