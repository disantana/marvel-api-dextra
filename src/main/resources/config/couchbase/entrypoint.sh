#!/usr/bin/env bash

/entrypoint.sh couchbase-server &

until curl -I -s http://localhost:8091/
do
    echo 'Waiting for Couchbase to start (retrying in 3 seconds)...'
    sleep 3
done

couchbase-cli cluster-init --cluster "couchbase://127.0.0.1" --cluster-name "couchbase" --cluster-username "admin" --cluster-password "password" --services "data,index,query" --cluster-ramsize 500 --cluster-index-ramsize 256 --index-storage-setting "memopt"