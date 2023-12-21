import haitham.com.au.common.Global

@SuppressWarnings(["GroovyAssignabilityCheck", 'GroovyParameterNamingConvention', 'GroovyConditional', 'GroovyMethodWithMoreThanThreeNegations', 'GroovyOverlyLongMethod', 'GroovyOverlyComplexMethod'])
def call(final Map<String, Object> params = [:]) {

    Global.set(this, params)

    pipeline {
        kubernetes {
            label "haitham-test"
            yaml """
            apiVersion: v1
            kind: Pod
            metadata:
              name: nginx-echo-hello-world
            spec:
              containers:
                - name: nginx
                  image: nginx:latest
                  command: ["/bin/sh"]
                  args:
                    - "-c"
                    - |
                      echo "Hello World";
                      while true; do sleep 3600; done
                    """
        }

        stages {
            stage('Hello') {
                steps {
                    echo 'Hello World'
                }
            }
        }
    }
}