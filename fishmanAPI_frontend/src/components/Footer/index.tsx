import {GithubOutlined} from '@ant-design/icons';
import {DefaultFooter} from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';
import {Tooltip} from "antd";

const Footer: React.FC = () => {
  const defaultMessage = 'Fishman出品';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      // @ts-ignore
      copyright={<>
        {`${currentYear} ${defaultMessage}`}
      </>}
      links={[
        {
          key: 'github',
          title: (
            <Tooltip title="查看本站技术及源码，欢迎 star">
              <GithubOutlined/> 支持项目
            </Tooltip>
          ),
          href: 'https://github.com/chenyufish/API-Open-Platform',
          blankTarget: true,
        }
      ]}
    />
  );
};
export default Footer;
