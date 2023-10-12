# inditex-exercise

This repo implements the level exercise for the INDITEX interview process. The exercise consists of
a
simple API that allows to fetch product information from database.

# Decisions

## Github

I have decided to use GitHub as the version control system for this project. I would like to
configure
some protections for the `main` branch, but it cannot be donne with a free account:

- `Required approvals` is disabled for `main` branch. It would be a good practice to have at least
  one approval before merging a PR. However, in this case, I am the only developer, so it makes no
  sense.
- `Require status checks to pass before merging` is enabled for `main` branch.
- `Force push` and `Deletion` are discouraged and of course disabled for `main` branch.

Despite the fact that enforcement rules cannot be applied to `main` branch, I will try to enforce
them
by myself.

## Github Actions

I have decided to use GitHub Action as the CI tool for this project. It provides developer user
with a great flexibility to configure the CI pipeline as code. The CI pipeline is triggered when
a new pull request is created. At this moment the pipeline just executes a `mvn verify` command
in order to check that the code compiles and all the tests pass.

## Self-Contained Unit Tests

At the time of writing a test, I personally prefer write a test method self-contained avoiding
calling
auxiliary methods and using magic numbers. Test code is not like production code and the main goal
of
the unit test is to check if a part of the system is working properly and helps developers to
understand
what is happening if something goes wrong. Self-Containing methods will provide developers with a
cleaner view on what is being tested without the need on jumping to other parts of the code.

The following article discusses the subject in more depth:

* [Why Good Developers Write Bad Unit Tests](https://mtlynch.io/good-developers-bad-tests/)

## Test Mutation

[PIT](https://pitest.org/) framework is used to introduce mutations testing on the project. As said
on the official project web page:

```
Faults (or mutations) are automatically seeded into your code, then your tests are run.
If your tests fail then the mutation is killed, if your tests pass then the mutation lived. 
```

This means that PIT runs unit test against automatically modified versions of the code. This
modification
should produce different result and cause the unit test to fail. If the test does not fail, maybe
test
suite is not exhaustive enough.

The following maven goal can be used to generate a mutation testing report of this project code
base:

```shell
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

NOTE: Mutation testing will be skipped on model classes. Many of those classes contains boiler-plate
code that is normally not tested.

## Changelog

I have decided to use [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) as the format for the
changelog.

## OpenAPI Specification

Following an API First approach, I have decided to
use [OpenAPI Specification](https://swagger.io/specification/)
to define the API. This specification will be used to generate the server stub and will serve as
documentation
for the API.  
The server stub will be generated
using [OpenAPI Generator maven plugin](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-maven-plugin/README.md)
using the `spring` generator.

## Exception Handling
I have decided to use controller advice to handle exceptions. This approach allows to centralize
exception handling and avoid code duplication.

## Request Trace
In order to help developers to trace requests through the system, I have decided to make use of `micrometer`
library to generate a unique trace id for each request. This trace id will be included in the response headers:
* `X-B3-TraceId`
* `X-B3-SpanId`
This traces will help developers to trace requests through a distributed system. It is also possible to use
some tools like [Zipkin](https://zipkin.io/) to visualize the traces.
