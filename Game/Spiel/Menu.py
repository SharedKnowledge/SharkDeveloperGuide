import pygame
from DrawObserver 		import DrawObserver

class Menu(DrawObserver):
	options = []
	currentOption = 0
	text = False # 0 = Image 1 = TXT

	def addOption(self, option):
		self.options.append(option)

	def draw(self, screen):
		pos = (500, 250)
		if(text):
			text = font.render( options[currentoption], 1, (10, 10, 10))
			
			screen.blit(text,pos)
		else:
			screen.blit(self.options[currentOption])

	def cycleOptions(self):
		sel.currentOption = self.currentOption + 1 % options.length

