import { browser, by, element } from 'protractor';

export class AppPage {
  appName = element(by.id('appName'));
  
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getAppName() {
    return this.appName.getText() as Promise<string>;
  }

}
