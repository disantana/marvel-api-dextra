FROM couchbase:latest

RUN mkdir files
#COPY entrypoint.sh /
#RUN chmod +x /entrypoint.sh
#ENTRYPOINT ./entrypoint.sh

COPY configure.sh /opt/couchbase
RUN chmod 777 /opt/couchbase/configure.sh
CMD ["opt/couchbase/configure.sh"]