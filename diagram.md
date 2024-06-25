```mermaid
classDiagram
    class App {
        +getGreeting() String
        +main(String[] args)
    }

    class Boat {
        -String name
        -double length
        <<abstract>>
        +Boat(String name, double length)
        +getName() String
        +getLength() double
        +getDetails() String
    }

    class SailboatBoat {
        -double depth
        +SailboatBoat(String name, double length, double depth)
        +getSailDepth() double
        +getDetails() String
    }

    class MotorboatBoat {
        -double enginePower
        +MotorboatBoat(String name, double length, double enginePower)
        +getEnginePower() double
        +getDetails() String
    }

    class MotorsailerBoat {
        -double depth
        -double enginePower
        +MotorsailerBoat(String name, double length, double depth, double enginePower)
        +getSailDepth() double
        +getEnginePower() double
        +getDetails() String
    }

    class CanoeBoat {
        +CanoeBoat(String name, double length)
        +getDetails() String
    }

    class BoatClubApp {
        -Registry registry
        -BoatClubInterface boatClubInterface
        +BoatClubApp()
        +start()
    }

    class BoatClubInterface {
        -Registry registry
        -Scanner scanner
        +BoatClubInterface(Registry registry)
        +run()
        +printMenu()
        +createMember()
        +listMembers()
        +addNewBoat(String memberId)
        +memberMenu(String memberId)
        +deleteMember(Member member)
        +deleteBoat(String memberId)
        +viewMemberDetails(String memberId)
        +loadRegistry()
        +saveRegistry()
    }

    class Member {
        -String name
        -String email
        -String memberId
        -List<Boat> boats
        -static Random random
        +Member(String name, String email)
        +Member(String name, String email, String memberId)
        +getName() String
        +getEmail() String
        +getMemberId() String
        +addBoat(Boat boat)
        +getBoats() List<Boat>
        +removeBoat(Boat boat) boolean
        +toString() String
    }

    class Registry {
        -List<Member> members
        +Registry()
        +addMember(Member member)
        +removeMember(Member member)
        +getMembers() List<Member>
        +findMemberById(String memberId) Member
        +loadFromFile(String filename) void
        +saveToFile(String filename) void
    }

    App --> BoatClubApp
    Boat <|-- SailboatBoat
    Boat <|-- MotorboatBoat
    Boat <|-- MotorsailerBoat
    Boat <|-- CanoeBoat
    BoatClubApp --> Registry
    BoatClubApp --> BoatClubInterface
    BoatClubInterface --> Registry
    BoatClubInterface --> Member
    Member --> Boat
    Registry --> Member