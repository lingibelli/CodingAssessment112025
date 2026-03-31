# Problem Statement
    As an enthusiastic card player and developer, 
    I want to create a program to play cards against the computer
    So that when I am bored I can play against an intelligent opponent.

# Assumptions
    1. UI is not decided. May be Web or Mobile or desktop application. Initial CLI as PoC.
    2. The type of card game is not decided yet.
    3. Game is played by a Single player against computer.
    4. A Deck could have any number of cards based on the game. For PoC lets consider 52 Cards, but configurable.
    5. Proof of Concept focuses on Deck, Cards, and Shuffle functionality.

# Constraints

1. UI is unknown → must decouple UI from core logic
2. Game rules are unknown → design must be extensible
3. Randomness (shuffle) must be testable
4. Time constraints → focus on minimal viable solution (PoC)    

# Architecture Decision

## Design Principles

- Separation of concerns between domain, application, and interface layers
- Domain logic independent of UI
- Use of Strategy Pattern for shuffle to improve testability and extensibility

## Decision
    Use Clean Architecture with separation of domain, application, and interface layers.

### Alternatives Considered

    1. Classic Monolithic Structure
        - Simple but tightly coupled. 
        - Not suitable when requirement on UI is not confirmed.

    2. MVC Pattern
        - UI-driven, less suitable for domain-first design

### Rationale

    - Enables flexibility for unknown UI
    - Improves testability
    - Supports future enhancements

### Learnings

    - Random behavior (shuffle) requires abstraction for testability
    - Designing with unknown requirements requires extensibility

# Development Environment

    1. Java programming language.
    2. Maven build tool
    3. Junit for testing
    4. Git for version Control
    5. GitHub Actions for Continuous Integration/Continuous Deployment

# High Level Design
    *Card Object
          - Suits [Hearts, Diamonds,Clubs,Spades] 
          - Rank - A,2,3,4,5,6,7,8,9,10,J,Q,K
    
    *Deck Object
        - Card Collection [52 cards for Poc, but configurable]
        - Shuffle Operation
        - Draw Operation [1 card at a time , but expandable]
    
    * ShuffleAlgorithm Object
        - Algorithm [Riffle,Overhand,Scramble]  
    * Game Engine Object [Responsible for core game flow and rules]
        - startGame
        - stopGame
        - playerTurn
        - scoreKeeping
        - dealCards
    

# Scaffold / Structure
card-game/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── components/        # Core domain (entities, value objects)
│   │   │   ├── Card.java
│   │   │   ├── Deck.java
│   │   │   └── ShuffleAlgorithm.java
│   │   │
│   │   ├── application/       # Use cases / business orchestration
│   │   │    └── GameEngine.java
│   │   │
│   │   ├── common/            # Shared utilities / constants
│   │   │   ├── Constants.java
│   │   │   └── Utils.java
│   │   │
│   │   └── interface/
│   │       └── cli/           # User interaction layer (CLI for now)
│   │           ├── Main.java
│   │           └── GameController.java
│   │
│   └── test/
│       ├── components/
│       │   ├── CardTest.java
│       │   └── DeckTest.java
│       │
│       ├── application/
│       │   └── GameServiceTest.java
│       │
│       └── interface/
│           └── cli/
│               └── GameControllerTest.java
│
└── README.md




# Proof of Concept:
 1. User should be able to shuffle cards
 2. User should be able to add cards to Deck
 3. User should be able to do various shuffle.
 4. User should be able to draw cards from the deck.
 5. Should be able to play the game in command line interface.
 6. Should be able to extend to any other interface with minimal change to core logic.



# Delivery Pipeline

# Pipeline Steps
    # On Commit
        - Compile code
        - Run unit tests
    # On Pull Request / Merge
        - Run full test suite        
    # Build Artifact
        - Package application (JAR)
    # Deployment to cloud



# Steps to do

## Setup
    - Setup project with Maven
    - Configure JUnit

## Unit Test [TDD Approach]

    - Write failing test for Deck initialization (should contain 52 cards)
    - Implement minimal code to pass test
    - Write test for shuffle behavior
    - Implement shuffle using strategy
    - Refactor safely

## Card Game Core Objects
    - Create Card entity
    - Create Deck entity
    - Implement ShuffleAlgorithm interface

## Application Objects
    - Create GameEngine

## Interface
    - Build simple CLI interface

## Testing
    - Write unit tests for all domain components 

## CI/CD
    - Setup GitHub Actions pipeline