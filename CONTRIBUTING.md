- [Contribute to `zosma`](#contribute-to-zosma)
    - [Closing policy for issues and merge requests](#closing-policy-for-issues-and-merge-requests)
    - [Issue tracker](#issue-tracker)
        - [Feature proposals](#feature-proposals)
        - [Issue tracker guidelines](#issue-tracker-guidelines)
        - [Issue weight](#issue-weight)
        - [Regression issues](#regression-issues)
        - [Technical debt](#technical-debt)
    - [Merge requests](#merge-requests)
        - [Merge request guidelines](#merge-request-guidelines)
        - [Merge request description format](#merge-request-description-format)
        - [Contribution acceptance criteria](#contribution-acceptance-criteria)
    - [Changes for Stable Releases](#changes-for-stable-releases)
    - [Definition of done](#definition-of-done)
    - [Style guides](#style-guides)
    - [Code of conduct](#code-of-conduct)

# Contribute to `zosma`

Thank you for your interest in contributing to `zosma`. This guide details how
to contribute to `zosma` in a way that is efficient for everyone.

## Closing policy for issues and merge requests

Out of respect for our volunteers, issues and merge requests not in line with 
the guidelines listed in this document may be closed without notice.

Please treat our volunteers with courtesy and respect, it will go a long way
towards getting your issue resolved.

Issues and merge requests should be in English and contain appropriate language
for audiences of all ages.

## Issue tracker

To get support for your particular problem please use the
issue tracker.

The issue tracker is for bugs concerning
the latest `zosma` release and [feature proposals](#feature-proposals).

When submitting an issue please conform to the issue submission guidelines
listed below. Not all issues will be addressed and your issue is more likely to
be addressed if you submit a merge request which partially or fully solves
the issue.

If it happens that you know the solution to an existing bug, please first
open the issue in order to keep track of it and then open the relevant merge
request that potentially fixes it.

### Feature proposals

For feature proposals, open an issue on the
issue tracker.

In order to help track the feature proposals, we have created a
`feature proposal` label. For the time being, users that are not members
of the project cannot add labels. You can instead ask one of the core team
members to add the label `feature proposal` to the issue.

Please keep feature proposals as small and simple as possible, complex ones
might be edited to make them small and simple.

Please submit Feature Proposals using the 'Feature Proposal' issue template provided on the issue tracker.

For changes in the interface, it can be helpful to create a mockup first.
If you want to create something yourself, consider opening an issue first to
discuss whether it is interesting to include this in `zosma`.

### Issue tracker guidelines

**Search the issue tracker** for similar entries before
submitting your own, there's a good chance somebody else had the same issue or
feature proposal. Show your support with an award emoji and/or join the
discussion.

Please submit bugs using the 'Bug' issue template provided on the issue tracker.
The text in the parenthesis is there to help you with what to include. Omit it
when submitting the actual issue. You can copy-paste it and then edit as you
see fit.

### Issue weight

Issue weight allows us to get an idea of the amount of work required to solve
one or multiple issues. This makes it possible to schedule work more accurately.

You are encouraged to set the weight of any issue. Following the guidelines
below will make it easy to manage this, without unnecessary overhead.

1. Set weight for any issue at the earliest possible convenience
1. If you don't agree with a set weight, discuss with other developers until
consensus is reached about the weight
1. Issue weights are an abstract measurement of complexity of the issue. Do not
relate issue weight directly to time. This is called [anchoring](https://en.wikipedia.org/wiki/Anchoring)
and something you want to avoid.
1. Something that has a weight of 1 (or no weight) is really small and simple.
Something that is 9 is rewriting a large fundamental part of `zosma`,
which might lead to many hard problems to solve. Changing some text in `zosma`
is probably 1, adding a new Git Hook maybe 4 or 5, big features 7-9.
1. If something is very large, it should probably be split up in multiple
issues or chunks. You can simply not set the weight of a parent issue and set
weights to children issues.

### Regression issues

Every release should have a corresponding issue on the issue tracker to keep
track of functionality broken by that release and any fixes that need to be
included in a patch release.

As outlined in the issue description, the intended workflow is to post one note
with a reference to an issue describing the regression, and then to update that
note with a reference to the merge request that fixes it as it becomes available.

If you're a contributor who doesn't have the required permissions to update
other users' notes, please post a new note with a reference to both the issue
and the merge request.

The release manager will update the notes in the regression issue as fixes are
addressed.

### Technical debt

In order to track things that can be improved in `zosma`'s codebase, we created
the ~"technical debt" label in [the issue tracker][issue-tracker].

This label should be added to issues that describe things that can be improved,
shortcuts that have been taken, code that needs refactoring, features that need
additional attention, and all other things that have been left behind due to
high velocity of development.

Everyone can create an issue, though you may need to ask for adding a specific
label, if you do not have permissions to do it by yourself. Additional labels
can be combined with the `technical debt` label, to make it easier to schedule
the improvements for a release.

Issues tagged with the `technical debt` label have the same priority like issues
that describe a new feature to be introduced in `zosma`, and should be scheduled
for a release by the appropriate person.

Make sure to mention the merge request that the `technical debt` issue is
associated with in the description of the issue.

## Merge requests

We welcome merge requests with fixes and improvements to `zosma` code, tests,
and/or documentation. The features we would really like a merge request for are
listed with the label `Accepting Merge Requests` on our issue tracker
and but other improvements are also welcome.

If you want to add a new feature that is not labeled it is best to first create
a feedback issue (if there isn't one already) and leave a comment asking for it
to be marked as `Accepting merge requests`. Please include screenshots or
wireframes if the feature will also change the UI.

### Merge request guidelines

If you can, please submit a merge request with the fix or improvements
including tests. If you don't know how to fix the issue but can write a test
that exposes the issue we will accept that as well. In general bug fixes that
include a regression test are merged quickly while new features without proper
tests are least likely to receive timely feedback. The workflow to make a merge
request is as follows:

1. Fork the project into your personal space on GitHub.com
1. Create a feature branch, branch away from `master`.
1. Write tests and code
1. If you are writing documentation, make sure to read the documentation styleguide
1. If you have multiple commits please combine them into one commit by
   squashing them
1. Push the commit(s) to your fork
1. Submit a merge request (MR) to the `development` branch
1. The MR title should describe the change you want to make
1. The MR description should give a motive for your change and the method you
   used to achieve it, see the merge request description format
1. If the MR changes the UI it should include before and after screenshots
1. Link any relevant issues in the merge request description and
   leave a comment on them with a link back to the MR
1. Be prepared to answer questions and incorporate feedback even if requests
   for this arrive weeks or months after your MR submission
1. When writing commit messages please follow [these](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html) [guidelines](http://chris.beams.io/posts/git-commit/).
1. If your merge request adds one or more migrations, make sure to execute all
   migrations on a fresh database before the MR is reviewed. If the review leads
   to large changes in the MR, do this again once the review is complete.
1. For more complex migrations, write tests.

Please keep the change in a single MR **as small as possible**. If you want to
contribute a large feature think very hard what the minimum viable change is.
Can you split the functionality? Can you only submit the backend/API code? Can
you start with a very simple UI? Can you do part of the refactor? The increased
reviewability of small MRs that leads to higher code quality is more important
to us than having a minimal commit log. The smaller an MR is the more likely it
is it will be merged (quickly). After that you can send more MRs to enhance it.

For examples of feedback on merge requests please look at already
closed merge requests. If you would like quick feedback
on your merge request feel free to mention one of the developers in the
core team.
Please ensure that your merge request meets the contribution acceptance criteria.

### Merge request description format

Please submit merge requests using the following template in the merge request
description area. Copy-paste it to retain the markdown format.

```
## What does this MR do?

## Are there points in the code the reviewer needs to double check?

## Why was this MR needed?

## What are the relevant issue numbers?

## Screenshots (if relevant)
```

### Contribution acceptance criteria

1. The change is as small as possible
1. Include proper tests and make all tests pass (unless it contains a test
   exposing a bug in existing code). Every new class should have corresponding
   unit tests, even if the class is exercised at a higher level, such as a feature test.
1. If you suspect a failing CI build is unrelated to your contribution, you may
   try and restart the failing CI job or ask a developer to fix the
   aforementioned failing test
1. Your MR initially contains a single commit (please use `git rebase -i` to
   squash commits)
1. Your changes can merge without problems (if not please merge `master`, never
   rebase commits pushed to the remote server)
1. Does not break any existing functionality
1. Fixes one specific issue or implements one specific feature (do not combine
   things, send separate merge requests if needed)
1. Migrations should do only one thing (e.g., either create a table, move data
   to a new table or remove an old table) to aid retrying on failure
1. Keeps the `zosma` code base clean and well structured
1. Contains functionality we think other users will benefit from too
1. Doesn't add configuration options or settings options since they complicate
   making and testing future changes
1. Changes after submitting the merge request should be in separate commits
   (no squashing). If necessary, you will be asked to squash when the review is
   over, before merging.
1. It conforms to the [style guides](#style-guides) and the following:
    - If your change touches a line that does not follow the style, modify the
      entire line to follow it. This prevents linting tools from generating warnings.
    - Don't touch neighbouring lines. As an exception, automatic mass
      refactoring modifications may leave style non-compliant.
1. If the merge request adds any new libraries they should be compatible with the AGPLv3 license. See the instructions in the license file for help if your MR fails the "license-finder" test with a "Dependencies that need approval" error.

## Changes for Stable Releases

Sometimes certain changes have to be added to an existing stable release.
Two examples are bug fixes and performance improvements. In these cases the
corresponding merge request should be updated to have the following:

1. A milestone indicating what release the merge request should be merged into.
1. The label "Pick into Stable"

This makes it easier for release managers to keep track of what still has to be
merged and where changes have to be merged into.
Like all merge requests the target should be development so all bugfixes are in development.

## Definition of done

If you contribute to `zosma` please know that changes involve more than just
code. We have the following definition of done. Please ensure you support
the feature you contribute through all of these steps.

1. Description explaining the relevancy (see following item)
1. Working and clean code that is commented where needed
1. Unit and integration tests that pass on the CI server
1. Documentation on the wiki
1. Changelog entry added
1. Reviewed and any concerns are addressed
1. Merged by the project lead
1. Community questions answered
1. Answers to questions radiated (in docs/wiki/etc.)

If you add a dependency in `zosma` (such as a new Gradle dependency) please
consider updating the following and note the applicability of each in your
merge request:

1. Note the addition in the release blog post (create one if it doesn't exist yet)
1. Upgrade guide
1. Installation guide
1. Test suite

## Style guides

1.  [Markdown](http://www.cirosantilli.com/markdown-styleguide)
1.  [Google Style](https://github.com/google/styleguide)

## Code of conduct

As contributors and maintainers of this project, we pledge to respect all
people who contribute through reporting issues, posting feature requests,
updating documentation, submitting pull requests or patches, and other
activities.

We are committed to making participation in this project a harassment-free
experience for everyone, regardless of level of experience, gender, gender
identity and expression, sexual orientation, disability, personal appearance,
body size, race, ethnicity, age, or religion.

Examples of unacceptable behavior by participants include the use of sexual
language or imagery, derogatory comments or personal attacks, trolling, public
or private harassment, insults, or other unprofessional conduct.

Project maintainers have the right and responsibility to remove, edit, or
reject comments, commits, code, wiki edits, issues, and other contributions
that are not aligned to this Code of Conduct. Project maintainers who do not
follow the Code of Conduct may be removed from the project team.

This code of conduct applies both within project spaces and in public spaces
when an individual is representing the project or its community.

Instances of abusive, harassing, or otherwise unacceptable behavior can be
reported by emailing `robocup.saxion@gmail.com`.

This Code of Conduct is adapted from the Contributor Covenant, version 1.1.0,
available at [http://contributor-covenant.org/version/1/1/0/](http://contributor-covenant.org/version/1/1/0/).
