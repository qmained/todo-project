Use this flags:

-XX:+UseG1GC  
-Xmx4G  
-Xms4G  
-Dkotlinx.coroutines.debug=off  
-Dio.netty.leakDetection.level=DISABLED  
-Dio.netty.buffer.checkAccessible=false  
-Dio.netty.buffer.checkBounds=false  
-XX:+AlwaysPreTouch  
-Djdk.graal.TuneInlinerExploration=1  
-Djdk.graal.Vectorization=true  
-Djdk.graal.OptDuplication=true  
-XX:ReservedCodeCacheSize=1024m  
-Djdk.graal.FullUnroll=true  
-Djdk.graal.EnterprisePartialUnroll=true  
-Djdk.graal.CompilerConfiguration=enterprise  
-Dreactor.netty.native=true  
