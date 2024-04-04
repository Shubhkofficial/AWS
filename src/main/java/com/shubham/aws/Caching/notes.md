1. S3 with AWS CloudFront (CDN) for serving static content
2. Amazon EC2 for local cache - in mem (on machine restart, it will get removed)
3. Elastic Cache - distributed and non local cache [ Redis/Memcached are frameworks]
Redis is better in terms of Data Structures - list, set, sortedsets - versatile
Can cache bigger size value for a key upto 512 MB for memcach its only a few MBs
Memcached only key value pair
Memcached is multicore and redis isn't so a bit better in performance and used for multithreaded applications
4. Amazon DynamoDB - NoSQL DB - horizontal scaling
Also offer caching using DynamoDB accelerator so when added on a table we can expect micro sec latency else milli sec