# Assassin-Manager-Game

## Overview
“Assassin” is a game often played on college campuses. Each person playing has a particular target that
he/she is trying to “assassinate.” Generally “assassinating” a person means finding them on campus in
public and acting on them in some way (e.g. saying “You’re dead,” squirting them with a water gun, or
tagging them). One of the things that makes the game more interesting to play in real life is that initially
each person knows only who they are assassinating; they don’t know who is trying to assassinate them,
nor do they know whom the other people are trying to assassinate.

## Rules
- You start out with a group of people who want to play the game
- A circular chain of assassination targets (called the “kill ring” in this program) is established.
- When someone is assassinated, the links need to be changed to “skip” that person

## Example
Let’s walk through an example with five people playing: Carol, Chris, Jim, Joe, Sally. We might decide
Joe should stalk Sally, Sally should stalk Jim, Jim should stalk Carol, Carol should stalk Chris, and Chris
should stalk Joe. In the actual linked list that implements this kill ring, Chris’s next reference would be
null. But, conceptually we can think of it as though the next person after Chris is Joe, the front person
in the list.
Here is a picture of this “kill ring”:
Joe -> Sally -> Jim -> Carol -> Chris
Then, suppose Sally assassinates Jim. Sally needs a new target, so we give her Jim’s target: Carol. The
kill ring becomes:
Joe -> Sally -> Carol -> Chris
front
If the first person in the kill ring is assassinated, the front of the list must adjust. If Chris kills Joe, the
list becomes:
Sally -> Carol -> Chris
