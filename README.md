# tale
Android logger using stack traces for automatic tag generation and method logging

Usage example:

To create and execute a log:

    new Tale("Some log message").tell();

This will result in a verbose-level log with a tag depending on from where the call to Tale() constructor has been made, for example:

    public class DemoActivity {
        public void log() {
            new Tale("Some log message").tell();
        }
    }
    // Output: V/DemoActivity: Some log message

Automatic tag creation works with inner classes too:

    public class DemoActivity {
        class SomeInnerClass {
            public void log() {
                new Tale("Some log message").tell();
            }
        }
    }
    // Output: V/DemoActivity{SomeInnerClass}: Some log message

Anonymous inner classes are no exception, but instead of ambiguous "$1" names, Tale will use 
the name of a class the anonymous inner class extends or the interface it implements, for example:

    public class DemoActivity {
        class SomeInnerClass {
            public void log() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        new Tale("Hello runnable!").at().tell();
                    }
                });
            }
        }
    }
    // Output: V\DemoActivity{SomeInnerClass{Runnable(1)}}: @run: Hello runnable!
    
When added extra method: at(), the logger will output the name of the function that encapsulates the call.

It's possible to set a pre-defined tag:

    new Tale("Some log message").tag("My Tag").tell():
    // Output: V/My Tag: Some log message

Set log level:

    new Tale("Some log message").how(Log.ERROR).tell();

And re-set attributes for pre-constructed Tale instances, such as:

    Tale tale = new Tale("Some log message");
    tale.story("Some other log message").tell();

