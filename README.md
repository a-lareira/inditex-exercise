# inditex-exercise
This repo implements the level exercise for the INDITEX interview process. The exercise consists of a 
simple API that allows to fetch product information from database.

# Decisions
## Github
I have decided to use GitHub as the version control system for this project. I would like to configure
some protections for the `main` branch, but it cannot be donne with a free account:
- `Required approvals` is disabled for `main` branch. It would be a good practice to have at least
  one approval before merging a PR. However, in this case, I am the only developer, so it makes no sense.
- `Require status checks to pass before merging` is enabled for `main` branch.
- `Force push` and `Deletion` are discouraged and of course disabled for `main` branch.

Despite the fact that enforcement rules cannot be applied to `main` branch, I will try to enforce them
by myself.

## Github Actions
I have decided to use GitHub Action as the CI tool for this project. It provides developer user
with a great flexibility to configure the CI pipeline as code. The CI pipeline is triggered when
a new pull request is created. At this moment the pipeline just executes a `mvn verify` command
in order to check that the code compiles and all the tests pass.