package examples.flowanalysis;

/**
 * Class that demonstrates application of "Avoid use of fields before
 * initialization in constructors and static initializers" rule.
 */
class UseBeforeInConstructorInitialization {

    static class User {

        String name;

        String password;

        User(String name, String password) {
            this.name = name;
            this.password = password;
            checkData();
        }

        void checkData() {
            // empty implementation
        }
    }

    static class RestrictedUser extends User {

        int minLoginLength = 7;

        RestrictedUser(String name, String password) {
            super(name, password);
        }

        // override User.checkData()
        @Override
        void checkData() {
            // BUG: minLoginLength is not yet initialized when checkData() is
            // called from
            // base class constructor
            if (super.name.length() < this.minLoginLength) {
                throw new IllegalArgumentException("length of name should be greater than 7");
            }
            // ..
        }
    }
}
