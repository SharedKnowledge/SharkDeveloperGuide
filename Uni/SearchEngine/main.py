from Url 		import Url
from UrlBank 	import UrlBank

class UrlStuff():	
	def main():
	    
	    u1 = Url("reddit.com", ["google.de", "xkcd.com", "google.de"], "random html")
	    u2 = Url("oracle.com", ["reddit.com","google.de","xkcd.com"], "rand html")
	    u3 = Url("google.de", ["reddit.com","reddit.com", "oracle.com"], "some html created in Django")
	    u4 = Url("xkcd.com", ["reddit.com"], "some very simple html")

	    ub = UrlBank()
	    
	    ub.addUrl(u1)
	    ub.addUrl(u2)
	    ub.addUrl(u3)
	    ub.addUrl(u4)

	    ub.printBank()

	if  __name__ =='__main__':main()