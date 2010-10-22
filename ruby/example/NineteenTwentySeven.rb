require "test/unit"
require "rubygems"
gem "selenium-client"
require "selenium/client"
require "titlecard"

class NineteenTwentySeven < Test::Unit::TestCase

  def setup
    @verification_errors = []
    @selenium = Selenium::Client::Driver.new \
      :host => "ondemand.saucelabs.com",
      :port => 4444,
      :browser => '{"username": "your username",' +
                    '"access-key": "your key",' +
                    '"os": "Windows 2003",' +
                    '"browser": "firefox",' +
                    '"browser-version": "3.",' +
                    '"user-extensions-url": "http://adam.goucher.ca/user-extensions/titlecard.js",' +
                    '"job-name": "1927"}',
      :url => "http://saucelabs.com",
      :timeout_in_second => 15

    @selenium.start_new_browser_session
  end
  
  def teardown
    @selenium.close_current_browser_session
    assert_equal [], @verification_errors
  end
    
  def test_titlecard
    @selenium.open "/"

    @selenium.show_title_card "Welcome to 1927!"
    sleep(5)
    @selenium.remove_title_card
  end
end