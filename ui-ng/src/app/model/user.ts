export class User {
    id: String;
    username: String;
    name: String;
    joinedAt: String;

    constructor(String id, String username, String name, String joinedAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
    }
}
