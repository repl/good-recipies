import { Component } from "@angular/core";

import {RecipeService} from './service/recipe.service';
import { User } from "./model/user";

declare var module: {
  id: string;
}

@Component({
  // moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [RecipeService]
})
export class AppComponent {
    title: String = "App Works, Really !";

    user: User;

    constructor(private recipeService: RecipeService) {
        let result = this.recipeService.load();
        result.subscribe(res => this.user = res);
    }
}
