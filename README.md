[![Build Status](https://circleci.com/gh/codacy/codacy-jshint.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-jshint)

create the docker: sbt docker:publishLocal

the docker is supposed to be run like so

```
docker run -it -v $srcDir:/src  codacy-engine-jshint:1.0
```

and $srcDir must contain a valid .codacy.json configuration