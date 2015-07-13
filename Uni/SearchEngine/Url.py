
class Url():
	def __init__(self, link, outgoing, html):
		
		self.name 		= link
		self.outLinks 	= {}
		self.incoming	= {}
		self.content	= html
		for ol in outgoing:
			self.addOut(ol)



		
	def addIn(self, host):
		if host in self.incoming:
			self.incoming[host] += 1
			print host, "++"
		else:
			print host," added"
			self.incoming[host] = 1 

	def addOut(self, dest):
		if dest in self.outLinks:
			self.outLinks[dest] += 1
		else:
			self.outLinks[dest] = 1

	def notify(self, newLinks, newSide):
		counter = 0
		if self.name in newLinks:
			for x in range(0, newLinks[self.name]):
				self.addIn(newSide)
		if newSide in self.outLinks:
			counter = self.outLinks[newSide]
		return counter

	def printCon(self):
		print self.name
		print "outgoing :",self.outLinks
		print "incoming :",self.incoming

	