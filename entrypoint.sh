#!/bin/bash

JAVA_OPTS=${JAVA_OPTS:="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8000 -Dsun.net.inetaddr.ttl=5"}

# We prefer PORT to be set up by the TaskDefinition, but listen on TCP/80 by default
export PORT=80

exec java $JAVA_OPTS -cp app:app/lib/* com.ryanshores.ecommerce.EcommerceApplication