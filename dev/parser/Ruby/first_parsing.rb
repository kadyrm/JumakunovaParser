require 'nokogiri'

doc = File.open('1.html') { |f| Nokogiri::HTML(f)}

puts doc