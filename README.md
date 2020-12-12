# DialogMenu
Bottom Sheet Dialog menu like ios


## Usage

Add it in your root build.gradle at the end of repositories:

##  add this
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  ##
    dependencies {
	        implementation 'com.github.bhanup212:dialogmenu:0.01'
	  }
  
  ##
      OptionsFragment().showOptions(supportFragmentManager,arrayOf("Edit","Delete"), "Cancel",object: OptionClickListener{
          override fun onClick(text: String, position: Int) {
              Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
          }

          override fun onNegativeButtonClick() {
              Toast.makeText(this@MainActivity, "Negative button clicked", Toast.LENGTH_SHORT).show()
        }
      })
