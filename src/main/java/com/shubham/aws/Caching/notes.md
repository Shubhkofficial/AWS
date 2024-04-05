1. S3 with AWS CloudFront (CDN) for serving static content
2. Amazon EC2 for local cache - in mem (on machine restart, it will get removed)
3. Elastic Cache - distributed and non local cache [ Redis/Memcached are frameworks]
Redis is better in terms of Data Structures - list, set, sortedsets - versatile
Can cache bigger size value for a key upto 512 MB for memcach its only a few MBs
Memcached only key value pair
Memcached is multicore and redis isn't so a bit better in performance and used for multithreaded applications
4. Amazon DynamoDB - NoSQL DB - horizontal scaling
Also offer caching using DynamoDB accelerator so when added on a table we can expect micro sec latency else milli sec


Add @RedisHash("Product")
public class Product implements Serializable to Entity Class
2. Set RedisConnectionFactory -- set host and port where redis server is running and return a new LettuceConnectionFactory(redisStandaloneConfiguration)
3. RedisTemplate<Key, Value> for AutoConfiguration - encode what needs to be serialize like K, V
4. Controller emthods - The anno from Spring framework
 @Cacheable(key = "#id", value = "Product")
 public Object getById(@PathVariable long id) {
    return productDAO.findProductById(id);
 }
5. Service to use redisTemplate 
HASH_KEY - "PRODUCT" string same as ENTITY Class
 public Product save(Product product) {
    redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
    return product;
}
redisTemplate.opsForHash().values(HASH_KEY);
redisTemplate.opsForHash().get(HASH_KEY, id);
redisTemplate.opsForHash().delete(HASH_KEY, id);