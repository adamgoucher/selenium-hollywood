module Selenium
  module Client
    class Driver
      def show_title_card(message)
          remote_control_command("showTitleCard", [message])
      end
      
      def hide_title_card()
          remote_control_command("removeTitleCard", [])
      end
    end
  end
end