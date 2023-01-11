public class Account{
        private double balance;

        public Account(){
            this.balance = 0.0;
        }
        
        public void deposit(double amount){
            balance = amount;
            System.out.println("New Balance: "+balance);
        }

        public void withdraw(double amount) throws NotEnoughMoneyException{
            if(amount > balance){
                throw new NotEnoughMoneyException(amount,balance);
            }
            balance = balance-(double)amount;
            System.out.println("New Balance: "+balance);
        }

        public double getBalance(){
            return balance;
        }
}