﻿//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Handy_Picking_Winform
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class HANDY_PICKING_Entities : DbContext
    {
        public HANDY_PICKING_Entities()
            : base("name=HANDY_PICKING_Entities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public DbSet<PRODUCTMF> PRODUCTMFs { get; set; }
        public DbSet<HANDY_PICKING_DETAIL> HANDY_PICKING_DETAIL { get; set; }
        public DbSet<HANDY_PICKING_MS> HANDY_PICKING_MS { get; set; }
        public DbSet<USER_MS> USER_MS { get; set; }
    }
}