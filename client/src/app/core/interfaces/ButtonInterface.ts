interface ButtonInterface {
    type?: 'button' | 'submit' | 'reset';
    to?: string;
    href?: string;
    label?: string;
    icon?: string;
    className?: string;
    rounded?: boolean;
    small?: boolean;
    medium?: boolean;
    larger?: boolean;
    disabled?: boolean;
    text?: boolean;
    leftIcon?: boolean;
    rightIcon?: boolean;
    style?: { [key: string]: any };
}