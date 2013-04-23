package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val setC = set1.incl(c)
    val d = new Tweet("d", "d body", 9)
    val setD = set1.incl(d)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
    
    val set6 = set1.incl(c).incl(new Tweet("a", "a body", 20)).incl(new Tweet("gmc", "gmc body", 30)).incl(new Tweet("largest", "largest body", 90))
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set c and set d") {
    new TestSets {
      assert(size(setC.union(setD)) === 2)
    }
  }

  test("union: c with nothing") {
    new TestSets {
      assert(size(setC.union(set1)) === 1)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      val unioned = set1.union(set5)
      assert(size(unioned) === 4)
    }
  }

  test("mostRetweeted: with set 6") {
    new TestSets {
      assert((set6.mostRetweeted).retweets === 90)
    }
  }
 
  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }
  
  test("tweetsets - influence - apple or google") {
    
    new TestSets {
    	val tweets = TweetReader.allTweets
    	//println("google tweets")
    	//GoogleVsApple.googleTweets.foreach(f => println(f.text))
    	//println("apple tweets")
    	//GoogleVsApple.appleTweets.foreach(f => println(f.text))
    	val appleAndGoogle = GoogleVsApple.googleTweets.union(GoogleVsApple.appleTweets)
    	//println((GoogleVsApple.googleTweets).mostRetweeted.text)
    	assert(!tweets.isEmpty)
    	assert(!appleAndGoogle.isEmpty)
    	
    	val trending = GoogleVsApple.trending
    	assert(GoogleVsApple.trending.head.retweets == 321)
    	//GoogleVsApple.trending.foreach(t => println(t))
    	
    
    }
    
  }

}
