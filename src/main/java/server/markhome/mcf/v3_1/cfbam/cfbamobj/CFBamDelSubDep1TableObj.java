// Description: Java 25 Table Object implementation for DelSubDep1.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamDelSubDep1TableObj
	implements ICFBamDelSubDep1TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDelSubDep1.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDelSubDep1.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> allDelSubDep1;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > > indexByTenantIdx;
	private Map< ICFBamDelDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > > indexByDefSchemaIdx;
	private Map< ICFBamDelDepByDelDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > > indexByDelDepIdx;
	private Map< ICFBamDelSubDep1ByDelTopDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > > indexByDelTopDepIdx;
	private Map< ICFBamDelSubDep1ByUNameIdxKey,
		ICFBamDelSubDep1Obj > indexByUNameIdx;
	public static String TABLE_NAME = "DelSubDep1";
	public static String TABLE_DBNAME = "delsubdep1";

	public CFBamDelSubDep1TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
		allDelSubDep1 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamDelSubDep1TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
		allDelSubDep1 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamDelSubDep1TableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamDelSubDep1TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allDelSubDep1 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepIdx = null;
		indexByUNameIdx = null;
		List<ICFBamDelSubDep1Obj> toForget = new LinkedList<ICFBamDelSubDep1Obj>();
		ICFBamDelSubDep1Obj cur = null;
		Iterator<ICFBamDelSubDep1Obj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelSubDep1Obj.
	 */
	@Override
	public ICFBamDelSubDep1Obj newInstance() {
		ICFBamDelSubDep1Obj inst = new CFBamDelSubDep1Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelSubDep1Obj.
	 */
	@Override
	public ICFBamDelSubDep1EditObj newEditInstance( ICFBamDelSubDep1Obj orig ) {
		ICFBamDelSubDep1EditObj edit = new CFBamDelSubDep1EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDelSubDep1Obj realiseDelSubDep1( ICFBamDelSubDep1Obj Obj ) {
		ICFBamDelSubDep1Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelSubDep1Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDelSubDep1Obj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					indexByDelDepIdx.remove( keyDelDepIdx );
				}
			}

			if( indexByDelTopDepIdx != null ) {
				ICFBamDelSubDep1ByDelTopDepIdxKey keyDelTopDepIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
				keyDelTopDepIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelTopDepIdx = indexByDelTopDepIdx.get( keyDelTopDepIdx );
				if( mapDelTopDepIdx != null ) {
					mapDelTopDepIdx.remove( keepObj.getPKey() );
					if( mapDelTopDepIdx.size() <= 0 ) {
						indexByDelTopDepIdx.remove( keyDelTopDepIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamDelSubDep1Obj)schema.getDelDepTableObj().realiseDelDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelTopDepIdx != null ) {
				ICFBamDelSubDep1ByDelTopDepIdxKey keyDelTopDepIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
				keyDelTopDepIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelTopDepIdx = indexByDelTopDepIdx.get( keyDelTopDepIdx );
				if( mapDelTopDepIdx != null ) {
					mapDelTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allDelSubDep1 != null ) {
				allDelSubDep1.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDelSubDep1Obj)schema.getDelDepTableObj().realiseDelDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDelSubDep1 != null ) {
				allDelSubDep1.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelTopDepIdx != null ) {
				ICFBamDelSubDep1ByDelTopDepIdxKey keyDelTopDepIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
				keyDelTopDepIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj > mapDelTopDepIdx = indexByDelTopDepIdx.get( keyDelTopDepIdx );
				if( mapDelTopDepIdx != null ) {
					mapDelTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelTopDepId( keepObj.getRequiredDelTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDelSubDep1Obj createDelSubDep1( ICFBamDelSubDep1Obj Obj ) {
		ICFBamDelSubDep1Obj obj = Obj;
		ICFBamDelSubDep1 rec = obj.getDelSubDep1Rec();
		schema.getCFBamBackingStore().getTableDelSubDep1().createDelSubDep1(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDelSubDep1Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1( CFLibDbKeyHash256 pkey ) {
		return( readDelSubDep1( pkey, false ) );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDelSubDep1Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDelSubDep1 readRec = schema.getCFBamBackingStore().getTableDelSubDep1().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDelSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelSubDep1Obj readCachedDelSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep1Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDelSubDep1( ICFBamDelSubDep1Obj obj )
	{
		final String S_ProcName = "CFBamDelSubDep1TableObj.reallyDeepDisposeDelSubDep1() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelSubDep1Obj existing = readCachedDelSubDep1( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDelSubDep1ByDelTopDepIdxKey keyDelTopDepIdx = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
		keyDelTopDepIdx.setRequiredDelTopDepId( existing.getRequiredDelTopDepId() );

		ICFBamDelSubDep1ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
		keyUNameIdx.setRequiredDelTopDepId( existing.getRequiredDelTopDepId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getDelSubDep2TableObj().deepDisposeDelSubDep2ByContDelDep1Idx( existing.getRequiredId() );

		if( indexByDelTopDepIdx != null ) {
			if( indexByDelTopDepIdx.containsKey( keyDelTopDepIdx ) ) {
				indexByDelTopDepIdx.get( keyDelTopDepIdx ).remove( pkey );
				if( indexByDelTopDepIdx.get( keyDelTopDepIdx ).size() <= 0 ) {
					indexByDelTopDepIdx.remove( keyDelTopDepIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getDelDepTableObj().reallyDeepDisposeDelDep( obj );
	}
	@Override
	public void deepDisposeDelSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep1Obj obj = readCachedDelSubDep1( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelSubDep1Obj lockDelSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep1Obj locked = null;
		ICFBamDelSubDep1 lockRec = schema.getCFBamBackingStore().getTableDelSubDep1().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDelSubDep1Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDelSubDep1", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readAllDelSubDep1() {
		return( readAllDelSubDep1( false ) );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readAllDelSubDep1( boolean forceRead ) {
		final String S_ProcName = "readAllDelSubDep1";
		if( ( allDelSubDep1 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamDelSubDep1Obj>();
			allDelSubDep1 = map;
			ICFBamDelSubDep1[] recList = schema.getCFBamBackingStore().getTableDelSubDep1().readAllDerived( null );
			ICFBamDelSubDep1 rec;
			ICFBamDelSubDep1Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep1Obj realised = (ICFBamDelSubDep1Obj)obj.realise();
			}
		}
		int len = allDelSubDep1.size();
		ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
		Iterator<ICFBamDelSubDep1Obj> valIter = allDelSubDep1.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readCachedAllDelSubDep1() {
		final String S_ProcName = "readCachedAllDelSubDep1";
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>();
		if( allDelSubDep1 != null ) {
			int len = allDelSubDep1.size();
			ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
			Iterator<ICFBamDelSubDep1Obj> valIter = allDelSubDep1.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDelSubDep1ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDelSubDep1Obj obj = readDelSubDep1( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readDelSubDep1ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep1Obj realised = (ICFBamDelSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
		Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDelSubDep1ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep1ByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep1Obj realised = (ICFBamDelSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
		Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readDelSubDep1ByDelDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep1ByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByDelDepIdx.containsKey( key ) ) {
			dict = indexByDelDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDelDepIdx( null,
				RelationId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep1Obj realised = (ICFBamDelSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
		Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId )
	{
		return( readDelSubDep1ByDelTopDepIdx( DelTopDepId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep1ByDelTopDepIdx";
		ICFBamDelSubDep1ByDelTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
		if( indexByDelTopDepIdx == null ) {
			indexByDelTopDepIdx = new HashMap< ICFBamDelSubDep1ByDelTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByDelTopDepIdx.containsKey( key ) ) {
			dict = indexByDelTopDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep1Obj>();
			ICFBamDelSubDep1Obj obj;
			ICFBamDelSubDep1[] recList = schema.getCFBamBackingStore().getTableDelSubDep1().readDerivedByDelTopDepIdx( null,
				DelTopDepId );
			ICFBamDelSubDep1 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep1Obj realised = (ICFBamDelSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelTopDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
		Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name )
	{
		return( readDelSubDep1ByUNameIdx( DelTopDepId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDelSubDep1Obj readDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelSubDep1ByUNameIdxKey,
				ICFBamDelSubDep1Obj >();
		}
		ICFBamDelSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		key.setRequiredName( Name );
		ICFBamDelSubDep1Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamDelSubDep1 rec = schema.getCFBamBackingStore().getTableDelSubDep1().readDerivedByUNameIdx( null,
				DelTopDepId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDelSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDelSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelSubDep1Obj readCachedDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep1Obj obj = null;
		obj = readCachedDelSubDep1( Id );
		return( obj );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedDelSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
				Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep1Obj obj;
			Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDelSubDep1ByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
				Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep1Obj obj;
			Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedDelSubDep1ByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>();
		if( indexByDelDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
			if( indexByDelDepIdx.containsKey( key ) ) {
				dict = indexByDelDepIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
				Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep1Obj obj;
			Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId )
	{
		final String S_ProcName = "readCachedDelSubDep1ByDelTopDepIdx";
		ICFBamDelSubDep1ByDelTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		ArrayList<ICFBamDelSubDep1Obj> arrayList = new ArrayList<ICFBamDelSubDep1Obj>();
		if( indexByDelTopDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict;
			if( indexByDelTopDepIdx.containsKey( key ) ) {
				dict = indexByDelTopDepIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep1Obj arr[] = new ICFBamDelSubDep1Obj[len];
				Iterator<ICFBamDelSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep1Obj obj;
			Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep1Obj> cmp = new Comparator<ICFBamDelSubDep1Obj>() {
			@Override
			public int compare( ICFBamDelSubDep1Obj lhs, ICFBamDelSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDelSubDep1Obj readCachedDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name )
	{
		ICFBamDelSubDep1Obj obj = null;
		ICFBamDelSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamDelSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep1Obj obj = readCachedDelSubDep1ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeDelSubDep1ByTenantIdx";
		ICFBamDelSubDep1Obj obj;
		List<ICFBamDelSubDep1Obj> arrayList = readCachedDelSubDep1ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDelSubDep1ByDefSchemaIdx";
		ICFBamDelSubDep1Obj obj;
		List<ICFBamDelSubDep1Obj> arrayList = readCachedDelSubDep1ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeDelSubDep1ByDelDepIdx";
		ICFBamDelSubDep1Obj obj;
		List<ICFBamDelSubDep1Obj> arrayList = readCachedDelSubDep1ByDelDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId )
	{
		final String S_ProcName = "deepDisposeDelSubDep1ByDelTopDepIdx";
		ICFBamDelSubDep1Obj obj;
		List<ICFBamDelSubDep1Obj> arrayList = readCachedDelSubDep1ByDelTopDepIdx( DelTopDepId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name )
	{
		ICFBamDelSubDep1Obj obj = readCachedDelSubDep1ByUNameIdx( DelTopDepId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelSubDep1Obj updateDelSubDep1( ICFBamDelSubDep1Obj Obj ) {
		ICFBamDelSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTableDelSubDep1().updateDelSubDep1( null,
			Obj.getDelSubDep1Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDelSubDep1TableObj().getClassCode() ) {
			obj = (ICFBamDelSubDep1Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDelSubDep1( ICFBamDelSubDep1Obj Obj ) {
		ICFBamDelSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1( null,
			obj.getDelSubDep1Rec() );
		Obj.forget();
	}

	@Override
	public void deleteDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep1Obj obj = readDelSubDep1(Id);
		if( obj != null ) {
			ICFBamDelSubDep1EditObj editObj = (ICFBamDelSubDep1EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDelSubDep1EditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeDelSubDep1ByIdIdx( Id );
	}

	@Override
	public void deleteDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamDelSubDep1Obj> iter = dict.values().iterator();
			ICFBamDelSubDep1Obj obj;
			List<ICFBamDelSubDep1Obj> toForget = new LinkedList<ICFBamDelSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByTenantIdx( null,
				TenantId );
		}
		deepDisposeDelSubDep1ByTenantIdx( TenantId );
	}

	@Override
	public void deleteDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDelSubDep1Obj> iter = dict.values().iterator();
			ICFBamDelSubDep1Obj obj;
			List<ICFBamDelSubDep1Obj> toForget = new LinkedList<ICFBamDelSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDelSubDep1ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( indexByDelDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict = indexByDelDepIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDelDepIdx( null,
				RelationId );
			Iterator<ICFBamDelSubDep1Obj> iter = dict.values().iterator();
			ICFBamDelSubDep1Obj obj;
			List<ICFBamDelSubDep1Obj> toForget = new LinkedList<ICFBamDelSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDelDepIdx( null,
				RelationId );
		}
		deepDisposeDelSubDep1ByDelDepIdx( RelationId );
	}

	@Override
	public void deleteDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId )
	{
		ICFBamDelSubDep1ByDelTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByDelTopDepIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		if( indexByDelTopDepIdx == null ) {
			indexByDelTopDepIdx = new HashMap< ICFBamDelSubDep1ByDelTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep1Obj > >();
		}
		if( indexByDelTopDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep1Obj> dict = indexByDelTopDepIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDelTopDepIdx( null,
				DelTopDepId );
			Iterator<ICFBamDelSubDep1Obj> iter = dict.values().iterator();
			ICFBamDelSubDep1Obj obj;
			List<ICFBamDelSubDep1Obj> toForget = new LinkedList<ICFBamDelSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelTopDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByDelTopDepIdx( null,
				DelTopDepId );
		}
		deepDisposeDelSubDep1ByDelTopDepIdx( DelTopDepId );
	}

	@Override
	public void deleteDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelSubDep1ByUNameIdxKey,
				ICFBamDelSubDep1Obj >();
		}
		ICFBamDelSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep1().newByUNameIdxKey();
		key.setRequiredDelTopDepId( DelTopDepId );
		key.setRequiredName( Name );
		ICFBamDelSubDep1Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByUNameIdx( null,
				DelTopDepId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep1().deleteDelSubDep1ByUNameIdx( null,
				DelTopDepId,
				Name );
		}
		deepDisposeDelSubDep1ByUNameIdx( DelTopDepId,
				Name );
	}
}