from Url import Url

class UrlBank():
	def __init__(self):
		self.urls = []

	
	def addUrl(self, url):
		if url.name not in self.urls:
			self.urls.append(url)
		for u in self.urls:
			counter = u.notify(url.outLinks, url.name)
			for x in range(0,counter):
				url.addIn(u.name)

	
	def printBank(self):
		for u in self.urls:
			u.printCon()