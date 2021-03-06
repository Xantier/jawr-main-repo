Jawr Global Preprocessors
-------------------------

Global Preprocessors are filters that Jawr applies to resources during
startup before the bundling process. In some cases, it is convenient to
preprocess all the JS or CSS files before making the bundle process,
because you need to gather information from all the files before making
your bundling process.

Jawr provides one built-in global preprocessor, which handles the image
sprite generation with smartsprites. Jawr uses a global preprocessor for
the image sprite generation when using smartsprites, because this
process requires to retrieve information from all the files before being
able to generate the sprites.

You can create and use your own global preprocessor, so here is how to
configure the filter chain for global preprocessing.

A global preprocessor will be only invoked once during application
startup before the bundling and the post process phases. The point is to
be able to make a treatment which need to be done on all the JS or CSS
resources, before the bundling process.

For configuration purposes, every global preprocessor has a unique name
key, which you use in a comma-separated property in the descriptor, for
instance:

            jawr.css.bundle.factory.global.preprocessors=smartsprites,myGlobalPreprocessor
                    

In this example, the global preprocessors set for **css** resources are
*smartsprites* and *myGlobalPreprocessor*, meaning that the
**smartsprites** and th **myGlobalPreprocessor** global preprocessors
will be processed for all css resources. The *smartsprites* global
preprocessor will be executed before *myGlobalPreprocessor*.


### Custom global preprocessor

You can also implement your own global preprocessor components (for js,
css, or both) to perform any functionality not offered by the included
one. To do that, you must create a class with a no-params constructor
that implements the interface
*net.jawr.web.resource.bundle.global.preprocessor.GlobalPreprocessor*.
This interface defines a single method:


            /**
             * Process the bundles for a type of resources.
             *  
             * @param ctx the processing context
             * @param bundles the list of bundles to process.
             */
            public void processBundles(GlobalPreprocessingContext ctx, List bundles);


The first parameter is an object which defines the global preprocessing
context and also gives you access to Jawr configuration plus other data
which may be useful under certain circumstances.

The second parameter is the list of bundles defined in your
configuration.

To use this global preprocessor in our application, we need to declare
it in the properties configuration, by giving it a name and declaring
the class so that Jawr may create an instance when starting up. The name
you give to your global preprocessor can then be used to define the
global factory properties, thus allowing you to create a chain that
combines your global preprocessor with those of Jawr.

The name and class are defined by declaring a property in the form
jawr.custom.global.preprocessor.\[name\].class=\[class\]. For example,
the following configuration would add one custom global preprocessor
named *myGlobalPreprocessor* and map to the css resources preprocessing
chains:


    jawr.custom.global.preprocessor.myGlobalPreprocessor.class=net.jawr.test.MyGlobalPreprocessor

    jawr.css.bundle.factory.global.preprocessors=myGlobalPreprocessor



 ------------------------------------------------------------------------

Grails users will unfortunately need to pack their classes in a jar and
add it to the lib folder of their application. The reason for this is a
known flaw in the Grails classloading strategy that keeps plugins from
accessing application classes.

------------------------------------------------------------------------


### Jawr included global preprocessors reference


**Smartsprites Css image preprocessor**
---------------------------------------

-   **Properties Key**: smartsprites

The smartsprites global preprocessor will generate the CSS sprites from
the smartsprites annotation from the CSS files. Check the [tutorial on
jawr image sprites](../tutorials/jawrSpriteImage.html) for more info.

