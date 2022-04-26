import {Tag} from "./tag";
import {Account} from "./account";

export interface Product {

  id: number;
  title: string;
  description: string;
  quantity: number;
  price: number;
  creator: Account;
  pictures: string[];
  listTags: Tag[];

}
